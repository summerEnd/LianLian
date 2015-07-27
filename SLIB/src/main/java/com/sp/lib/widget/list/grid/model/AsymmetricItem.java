package com.sp.lib.widget.list.grid.model;

import android.os.Parcelable;

public interface AsymmetricItem extends Parcelable {

  int getColumnSpan();

  int getRowSpan();
}
