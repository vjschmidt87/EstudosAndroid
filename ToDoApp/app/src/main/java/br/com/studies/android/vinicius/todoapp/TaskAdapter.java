package br.com.studies.android.vinicius.todoapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vinicius.schmidt on 30/03/2017.
 */

public class TaskAdapter extends ArrayAdapter<Task> {
    Context context;
    int layoutResourceId;
    ArrayList<Task> data = null;

    public TaskAdapter(Context context, int layoutResourceId, ArrayList<Task> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TaskHolder holder = null;

        if(row == null || row.getTag() == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TaskHolder();
            holder.taskId = (TextView) row.findViewById(R.id.task_id);
            holder.taskTitle = (TextView) row.findViewById(R.id.task_title);
        } else {
            holder = (TaskHolder) row.getTag();
        }

        Task task = data.get(position);
        holder.taskId.setText(String.valueOf(task.id));
        holder.taskTitle.setText(task.title);

        return row;
    }

    static class  TaskHolder {
        TextView taskId;
        TextView taskTitle;
    }
}
