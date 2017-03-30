package br.com.studies.android.vinicius.todoapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.studies.android.vinicius.db.TaskDbHelper;
import br.com.studies.android.vinicius.db.TaskEntry;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TaskDbHelper mHelper;
    private ListView mTaskListView;
    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new TaskDbHelper(this);
        mTaskListView = (ListView) findViewById(R.id.todo_list);
        View header = (View)getLayoutInflater().inflate(R.layout.header_row, null);
        mTaskListView.addHeaderView(header);
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.task_add:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("New task")
                        .setMessage("What to do?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(TaskEntry.COL_TASK_TITLE, task);
                                db.insertWithOnConflict(TaskEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //query(tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy);
        Cursor cursor = db.query(TaskEntry.TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Task task = new Task (cursor.getInt(cursor.getColumnIndex(TaskEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(TaskEntry.COL_TASK_TITLE)));
            taskList.add(task);
            Log.d(TAG, task.id + " " + task.title);
        }

        if (mAdapter == null) {
            mAdapter = new TaskAdapter(this, R.layout.item_todo, taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskIdView = (TextView) parent.findViewById(R.id.task_id);
        String taskId = String.valueOf(taskIdView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(TaskEntry.TABLE, TaskEntry._ID + " = ?", new String[]{taskId});
        db.close();
        updateUI();
    }
}
