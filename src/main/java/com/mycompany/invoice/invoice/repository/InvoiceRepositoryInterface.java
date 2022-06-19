package com.mycompany.invoice.invoice.repository;

import com.mycompany.invoice.core.entity.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvoiceRepositoryInterface extends CrudRepository<Invoice, String> {}
