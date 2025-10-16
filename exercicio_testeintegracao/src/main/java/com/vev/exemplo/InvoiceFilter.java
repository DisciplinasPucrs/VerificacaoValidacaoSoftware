package com.vev.exemplo;

import java.util.List;

public class InvoiceFilter {

    private final IssuedInvoices issuedInvoices;

    public InvoiceFilter(IssuedInvoices issuedInvoices) {
        this.issuedInvoices = issuedInvoices;
    }

    public List<Invoice> lowValueInvoices() {
        List<Invoice> all = issuedInvoices.all();
        return all.stream()
                .filter(invoice -> invoice.getValue() < 100)
                .toList();
    }
}
