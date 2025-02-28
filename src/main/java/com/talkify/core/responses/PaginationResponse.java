package com.talkify.core.responses;

import java.util.List;

public class PaginationResponse<Item> {
  private final List<Item> items;
  private final int itemsPerPage;

  public PaginationResponse(List<Item> items, int itemsPerPage) {
    this.items = items;
    this.itemsPerPage = itemsPerPage;
  }

  public List<Item> getItems() {
    return items;
  }

  public int getItemsPerPage() {
    return itemsPerPage;
  }
}
