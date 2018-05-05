package jhotel.com.jhotel_android_mochfahmi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MenuListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<Hotel> listHotel;
    private HashMap<Hotel, ArrayList<Room>> childMapping;

    public MenuListAdapter(Context context, ArrayList<Hotel> listHotel, HashMap<Hotel, ArrayList<Room>> childMapping) {
        this._context = context;
        this.listHotel = listHotel;
        this.childMapping=childMapping;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.childMapping.get(this.listHotel.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = ((Room) getChild(groupPosition, childPosition)).toString();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_room, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.child);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childMapping.get(this.listHotel.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listHotel.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listHotel.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = ((Hotel) getGroup(groupPosition)).getNama();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_hotel, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.groupHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}