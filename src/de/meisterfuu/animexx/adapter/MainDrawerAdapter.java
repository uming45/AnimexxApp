package de.meisterfuu.animexx.adapter;

import java.util.ArrayList;

import de.meisterfuu.animexx.R;
import de.meisterfuu.animexx.objects.DrawerObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainDrawerAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<DrawerObject> mItems;
	LayoutInflater inflater;


	public MainDrawerAdapter(Context context) {
		this.mContext = context;
		
		mItems = new ArrayList<DrawerObject>();
		
		DrawerObject temp = new DrawerObject();
		temp.setIconId(R.drawable.ic_stat_ens);
		temp.setTitle("ENS Design 1");
		temp.setSubtitle("");
		this.addItem(temp);
		
		temp = new DrawerObject();
		temp.setIconId(R.drawable.ic_stat_ens);
		temp.setTitle("ENS Design 2");
		temp.setSubtitle("");
		this.addItem(temp);
		
		temp = new DrawerObject();
		temp.setIconId(R.drawable.ic_stat_ens);
		temp.setTitle("ENS Design 3");
		temp.setSubtitle("");
		this.addItem(temp);
		
		temp = new DrawerObject();
		temp.setIconId(R.drawable.ic_stat_ens);
		temp.setTitle("ENS Design 4");
		temp.setSubtitle("");
		this.addItem(temp);
		
		temp = new DrawerObject();
		temp.setIconId(R.drawable.ic_stat_ens);
		temp.setTitle("RPG (WIP)");
		temp.setSubtitle("");
		this.addItem(temp);
		
		temp = new DrawerObject();
		temp.setIconId(R.drawable.ic_stat_ens);
		temp.setTitle("Events (WIP)");
		temp.setSubtitle("");
		this.addItem(temp);
		
		temp = new DrawerObject();
		temp.setIconId(R.drawable.ic_stat_ens);
		temp.setTitle("Chat (WIP)");
		temp.setSubtitle("");
		this.addItem(temp);
		
	}
	
	public void addItem(DrawerObject pItem){
		mItems.add(pItem);
	}
	
	public void addItemAt(DrawerObject pItem, int pPosition){
		mItems.add(pPosition, pItem);
	}
	
	public void addItem(int pPosition){
		mItems.remove(pPosition);
	}


	@Override
	public int getCount() {
		return mItems.size();
	}


	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}


	@Override
	public long getItemId(int position) {
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView txtTitle;
		TextView txtSubTitle;
		ImageView imgIcon;

		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);

		DrawerObject item = mItems.get(position);
		
		txtTitle = (TextView) itemView.findViewById(R.id.drawer_item_title);
		txtSubTitle = (TextView) itemView.findViewById(R.id.drawer_item_subtitle);
		imgIcon = (ImageView) itemView.findViewById(R.id.drawer_item_icon);

		// Set test
		txtTitle.setText(item.getTitle());
		txtSubTitle.setText(item.getSubtitle());

		//Set icon
		if(item.getIconId() != 0){
			imgIcon.setImageResource(item.getIconId());
		}


		return itemView;
	}

}
