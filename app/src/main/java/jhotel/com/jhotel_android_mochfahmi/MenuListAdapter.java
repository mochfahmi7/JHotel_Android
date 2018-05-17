package jhotel.com.jhotel_android_mochfahmi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

/**
 * Class ini merupakan class MenuListAdapter, yaitu class untuk mengatur list pada expandable list.
 * version 15/05/2018
 */
public class MenuListAdapter extends BaseExpandableListAdapter implements Filterable {


    private Context _context;
    private ArrayList<Hotel> listHotel;
    private HashMap<Hotel, ArrayList<Room>> childMapping;
    private ArrayList<Hotel> filteredHotel;
    private HashMap<Hotel, ArrayList<Room>> filteredChild = new HashMap<>();

    MangaNameFilter filter;

    public MenuListAdapter(Context context, ArrayList<Hotel> listHotel, HashMap<Hotel, ArrayList<Room>> childMapping) {
        this._context = context;
        this.listHotel = listHotel;
        this.childMapping = childMapping;
        filteredHotel = listHotel;
        filteredChild = childMapping;
        getFilter();
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.filteredChild.get(this.filteredHotel.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = ((Room) getChild(groupPosition, childPosition)).toString();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_room, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.child);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.filteredChild.get(this.filteredHotel.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.filteredHotel.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.filteredHotel.size();
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


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new MangaNameFilter();
        }
        return filter;
    }

    private class MangaNameFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // NOTE: this function is *always* called from a background thread, and
            // not the UI thread.
//            constraint = edit.getText().toString().toLowerCase();
            FilterResults result = new FilterResults();
            FilterResults result2 = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
//                detailsList=detailsSer.GetAlldetails();
//                dupCatList=detailsList;

                ArrayList<Hotel> filt = new ArrayList<>();
                String filter = constraint.toString().toLowerCase();


                HashMap<Hotel, ArrayList<Room>> filteredChildT = new HashMap<>();

                for (Hotel h : listHotel) {
                    if (h.getNama().contains(filter)) {
                        filt.add(h);
                        filteredChildT.put(h, childMapping.get(h.getNama()));
                    }
                }

                filteredChild = filteredChildT;
                result.count = filteredChildT.size();
                result.values = filteredChildT;

                result2.count = filt.size();
                result2.values = filt;
            } else {
//                result.count = listHotel.size();
//                result.values = listHotel;

                result.count = childMapping.size();
                result.values = childMapping;
            }
            return result;
        }

        //        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults result) {
            // NOTE: this function is *always* called from the UI thread.

            filteredChild = (HashMap<Hotel, ArrayList<Room>>) result.values;
//            filteredHotel = (ArrayList<Hotel>)result

//            ArrayList<Integer> IdList = new ArrayList<Integer>();
//            IdList.clear();
//            for(int i=0;i<filtered.size();i++)
//            {
//                IdList.add(filtered.get(i).catID);
//            }
//
//            HashSet<Integer> hashSet = new HashSet<Integer>(IdList);
//            midList = new ArrayList<Integer>(hashSet) ;
//            Collections.sort(midList);
//            Adapter = new CategoryListAdapter(context, R.layout.list1, R.layout.list2, filtered, midList);
//            List.setAdapter(Adapter);
            notifyDataSetChanged();
        }
    }
}