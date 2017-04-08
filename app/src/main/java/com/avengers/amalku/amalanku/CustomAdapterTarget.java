package com.avengers.amalku.amalanku;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class CustomAdapterTarget extends ArrayAdapter{
    ModelTarget[] modelItems = null;
    Context context;
    public CustomAdapterTarget(Context context, ModelTarget[] resource) {
        super(context,R.layout.row,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row_target, parent, false);
        TextView tv1 = (TextView) convertView.findViewById(R.id.textView3);
        TextView tv2 = (TextView) convertView.findViewById(R.id.textView4);
        ImageButton imgb = (ImageButton) convertView.findViewById(R.id.imageButton3);
        String jml = modelItems[position].getJumlah().toString();
        tv1.setText(modelItems[position].getNama());
        tv2.setText(jml+" "+modelItems[position].getSatuan());
        imgb.setImageResource(modelItems[position].getImageId());
        return convertView;
    }
}