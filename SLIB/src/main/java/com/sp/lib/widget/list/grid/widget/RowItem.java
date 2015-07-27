package com.sp.lib.widget.list.grid.widget;


import com.sp.lib.widget.list.grid.model.AsymmetricItem;

public final class RowItem<T extends AsymmetricItem> {

  private final T item;
  private final int index;

  public RowItem(int index, T item) {
    this.item = item;
    this.index = index;
  }

  public T getItem() {
    return item;
  }

  public int getIndex() {
    return index;
  }
}
