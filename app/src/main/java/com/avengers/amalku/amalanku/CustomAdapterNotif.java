package com.avengers.amalku.amalanku;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

public class CustomAdapterNotif extends ArrayAdapter{
    ModelNotif[] modelItems = null;
    Context context;
    public CustomAdapterNotif(Context context, ModelNotif[] resource) {
        super(context,R.layout.row,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row_notif, parent, false);
        Switch sw = (Switch) convertView.findViewById(R.id.switch1);
        sw.setText(modelItems[position].getName());
        if(modelItems[position].getValue() == 1)
            sw.setChecked(true);
        else
            sw.setChecked(false);
        return convertView;
    }
}