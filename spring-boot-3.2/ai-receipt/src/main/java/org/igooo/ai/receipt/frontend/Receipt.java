package org.igooo.ai.receipt.frontend;

import java.util.List;

public record Receipt(String merchant, double total, List<Item> items) {

	public record Item(String name, int quantity, double price) {
	}
}
