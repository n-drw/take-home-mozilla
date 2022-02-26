package com.pocket.homework;

import static kotlin.text.StringsKt.isBlank;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.pocket.homework.databinding.ItemRowBinding;
import com.pocket.homework.internal.Item;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Adapter for showing a list of simple item rows in a {@link RecyclerView}.
 */
final class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
	ItemSortOrder itemSortOrder;
	Context context;

	public MyListAdapter(Context context, ItemSortOrder itemSortOrder) {
		this.context = context;
		this.itemSortOrder = itemSortOrder;
	}

	final class ViewHolder extends RecyclerView.ViewHolder {
		@NotNull private final ItemRowBinding views;

		private String formattedDate;
		private String articleUrl;

		private final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd '–' hh:mm a z", Locale.US);

		ViewHolder(ItemRowBinding views) {
			super(views.getRoot());
			this.views = views;
		}

		@RequiresApi(api = Build.VERSION_CODES.P)
		void bind(Item item) {
			views.title.setText(item.getTitle());
			views.author.setText(item.getAuthor());
			views.excerpt.setText(item.getExcerpt());
			// TODO Convert to human readable date

			formattedDate = formatter.format(new Date(Long.parseLong(String.valueOf(item.getPublishedAt()))));

			// TODO append item URL's domain after the date, separated by this character: ·
			articleUrl = item.getUrl();
			articleUrl = articleUrl.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)","");
			articleUrl = articleUrl.split("/")[0];
			views.details.setText(formattedDate + " · " + articleUrl);

			if (item.getThumbnailUrl() != null && !isBlank(item.getThumbnailUrl())) {
				Picasso.get()
						.load(item.getThumbnailUrl())
						.into(views.thumbnail);
			}
		}
	}

	private final List<Item> items = new ArrayList<>();

	@NonNull @NotNull @Override
	public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
		return new ViewHolder(
				ItemRowBinding.inflate(
						LayoutInflater.from(parent.getContext()),
						parent,
						false
				)
		);
	}

	@RequiresApi(api = Build.VERSION_CODES.P)
	@Override public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
		holder.bind(items.get(position));
	}

	@Override public int getItemCount() {
		return items.size();
	}

	public void updateItemsFilters(@Nullable ItemSortOrder order) {
		if (order != null) {
			this.itemSortOrder = order;
//			switch (order.getType()) {
//			}
		}
		updateItems(items);
	}

	public void updateItems(List<Item> items) {
		this.items.clear();
		this.items.addAll(0, items);
		notifyDataSetChanged();
	}

}
