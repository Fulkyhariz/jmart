package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.*;
import com.fulkyJmartRK.ObjectPoolThread;
import com.fulkyJmartRK.Payment;
import com.fulkyJmartRK.dbjson.*;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

/**
 * class yang digunakan untuk mengatur payment dari aplikasi
 * @author fulky hariz
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    public static final long DELIVERED_LIMIT_MS = 3000;
    public static final long ON_DELIVERY_LIMIT_MS = 3000;
    public static final long ON_PROGRESS_LIMIT_MS = 3000;
    public static final long WAITING_CONF_LIMIT_MS = 3000;

    @JsonAutowired(filepath = "C:/Java/jmart/json/randomPaymentList.json", value = Payment.class)
    public static JsonTable<Payment> paymentTable;

    public static ObjectPoolThread<Payment> poolThread;

    @Override
    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    /**
     * merupakan method untuk membuat suatu payment baru, payment baru ditambahkan
     * ketika user membeli suatu produk
     * @param buyerId id dari akun pembeli
     * @param productId id dari produk
     * @param productCount jumlah produk yang dibeli
     * @param shipmentAddress alamat pengiriman
     * @param shipmentPlan jenis pengiriman
     * @return class payment berisi payment yang telah dibuat
     */
    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
        Account account = new AccountController().getById(buyerId);
        Product product = new ProductController().getById(productId);
        Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
        Payment payment = new Payment(buyerId, productId, productCount, shipment);
        System.out.println(productCount);
        double totalPay = payment.getTotalPay(product);
        if(account.balance > totalPay) {
            account.balance -= totalPay;
            Payment.Record record = new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Pesan");
            payment.history.add(record);
            paymentTable.add(payment);
            //poolThread.add(payment);
            return payment;
        }
        return null;
    }

    /**
     * method yang digunakan untuk menandakan bahwa payment telah diterima
     * @param id payment id
     * @return boolean menandakan payment diterima atau tidak
     */
    @PostMapping("/{id}/accept")
    public boolean accept(@RequestParam int id) {
        Payment payment = new PaymentController().getById(id);
        if(payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION) {
            Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Pesan");
            payment.history.add(record);
            return true;
        }
        return false;
    }

    /**
     * method yang digunakan untuk menandakan bahwa payment dibatalkan
     * @param id payment id
     * @return boolean menandakan payment dibatalkan atau tidak
     */
    @PostMapping("/{id}/cancel")
    public boolean cancel(@RequestParam int id) {
        Payment payment = new PaymentController().getById(id);
        if(payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION) {
            Payment.Record record = new Payment.Record(Invoice.Status.CANCELLED, "Pesan");
            payment.history.add(record);
            return true;
        }
        return false;
    }

    /**
     * method yang digunakan untuk menandakan bahwa payment telah disubmit oleh pembeli
     * @param id payment id
     * @return boolean menandakan submit payment berhasil atau tidak
     */
    @PostMapping("/{id}/submit")
    public boolean submit(@RequestParam int id, @RequestParam String receipt) {
        Payment payment = new PaymentController().getById(id);
        if(payment.history.get(payment.history.size() - 1).status == Invoice.Status.ON_PROGRESS && !payment.shipment.receipt.isBlank()) {
            payment.shipment.receipt = receipt;
            Payment.Record record = new Payment.Record(Invoice.Status.ON_DELIVERY, "Pesan");
            payment.history.add(record);
            return true;
        }
        return false;
    }

    /**
     * method yang digunakan untuk mencatat waktu perubahan status pesanan
     * berdasarkan instance variable time limit
     * @param payment class payment berisi payment yang telah dilakukan
     * @return boolean menandakan update status berhasil atau tidak
     */
    private static boolean timekeeper (@RequestParam Payment payment){
        Date timeNow = Calendar.getInstance().getTime();
        if (payment.history.size() != 0) {
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if (lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)) {
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
                return true;
            } else if ((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)) {
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
                return true;
            } else if (lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS) {
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "DELIVERED"));
                return true;
            } else if (lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS) {
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "FINISHED"));
                return true;
            }
        }
        return false;
    }
}
