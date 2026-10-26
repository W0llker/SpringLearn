package org.example.JDBS.dto;

public record CustomersFilter(String full_name, String city, int limit, int offset) {
}
