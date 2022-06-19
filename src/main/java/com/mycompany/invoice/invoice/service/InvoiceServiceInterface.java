package com.mycompany.invoice.invoice.service;

import com.mycompany.invoice.core.entity.invoice.Invoice;
import com.mycompany.invoice.invoice.repository.InvoiceRepositoryInterface;

public interface InvoiceServiceInterface {
    Iterable<Invoice> list();
    Invoice getInvoiceByNumber(String number);
    void setInvoiceRepository(InvoiceRepositoryInterface invoiceRepository);
    Invoice createInvoice(Invoice invoice);
}
