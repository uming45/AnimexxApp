package de.meisterfuu.animexx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.meisterfuu.animexx.R;
import de.meisterfuu.animexx.objects.DrawerObject;

public class MainDrawerAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<DrawerObject> mItems;
    LayoutInflater inflater;


    public MainDrawerAdapter(Context context) {
        this.mContext = context;

        mItems = new ArrayList<DrawerObject>();

        DrawerObject temp;

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("Home");
        temp.setSubtitle("");
        temp.setCode("HOME");
        this.addItem(temp);

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("ENS");
        temp.setSubtitle("");
        temp.setCode("ENS");
        this.addItem(temp);

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("RPG");
        temp.setSubtitle("");
        temp.setCode("RPG");
        this.addItem(temp);

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("Events");
        temp.setSubtitle("");
        temp.setCode("EVENT");
        this.addItem(temp);

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("Chat");
        temp.setSubtitle("");
        temp.setCode("CHAT");
        this.addItem(temp);

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("Kontakte");
        temp.setSubtitle("");
        temp.setCode("CONTACTS");
        this.addItem(temp);

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("Manga");
        temp.setSubtitle("");
        temp.setCode("MANGA");
        this.addItem(temp);

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("Einstellungen");
        temp.setSubtitle("");
        temp.setCode("SETTINGS");
        this.addItem(temp);

        temp = new DrawerObject();
        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
        temp.setTitle("Feedback");
        temp.setSubtitle("");
        temp.setCode("FEEDBACK");
        this.addItem(temp);

//        temp = new DrawerObject();
//        temp.setIconId(R.drawable.ens_flags_forwarded_blue);
//        temp.setTitle("WebDAV(WIP)");
//        temp.setSubtitle("");
//        temp.setCode("FILES");
//        this.addItem(temp);
    }

    public void addItem(DrawerObject pItem) {
        mItems.add(pItem);
    }

    public void addItemAt(DrawerObject pItem, int pPosition) {
        mItems.add(pPosition, pItem);
    }

    public void addItem(int pPosition) {
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
        ImageView imgIcon;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);

        DrawerObject item = mItems.get(position);

        txtTitle = (TextView) itemView.findViewById(R.id.drawer_item_title);
        imgIcon = (ImageView) itemView.findViewById(R.id.drawer_item_icon);

        // Set test
        txtTitle.setText(item.getTitle());

        //Set icon
        if (item.getIconId() != 0) {
            imgIcon.setImageResource(item.getIconId());
        }


        return itemView;
    }

}
