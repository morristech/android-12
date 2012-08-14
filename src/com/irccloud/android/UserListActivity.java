package com.irccloud.android;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;

public class UserListActivity extends BaseActivity implements UsersListFragment.OnUserSelectedListener {
	int cid;
	long bid;
	String channel;
	String selected_name;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState != null && savedInstanceState.containsKey("cid")) {
        	cid = savedInstanceState.getInt("cid");
        	bid = savedInstanceState.getLong("bid");
        	channel = savedInstanceState.getString("channel");
        }
    }

    @Override
    public void onResume() {
    	super.onResume();
    	if(getIntent() != null && getIntent().hasExtra("cid")) {
	    	cid = getIntent().getIntExtra("cid", 0);
	    	bid = getIntent().getLongExtra("bid", 0);
	    	channel = getIntent().getStringExtra("name");
    	}
    	getSupportActionBar().setTitle(channel + " members");
    }
    
    @Override
    public void onSaveInstanceState(Bundle state) {
    	super.onSaveInstanceState(state);
    	state.putInt("cid", cid);
    	state.putLong("bid", bid);
    	state.putString("channel", channel);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onUserSelected(int c, String channel, String name) {
		selected_name = name;
		UsersDataSource u = UsersDataSource.getInstance();
		UsersDataSource.User user = u.getUser(cid, channel, name);
		final CharSequence[] items = {"Open", "Invite to a channel", "Ignore", "Deop", "Kick", "Ban"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(user.nick + "\n(" + user.hostmask + ")");
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	switch(item) {
		    	case 0:
		    		BuffersDataSource b = BuffersDataSource.getInstance();
		    		BuffersDataSource.Buffer buffer = b.getBufferByName(cid, selected_name);
		    		Intent i = new Intent(UserListActivity.this, MessageActivity.class);
		    		if(buffer != null) {
			    		i.putExtra("cid", buffer.cid);
			    		i.putExtra("bid", buffer.bid);
			    		i.putExtra("name", buffer.name);
			    		i.putExtra("last_seen_eid", buffer.last_seen_eid);
			    		i.putExtra("min_eid", buffer.min_eid);
			    		i.putExtra("type", buffer.type);
			    		i.putExtra("joined", 1);
			    		i.putExtra("archived", buffer.archived);
		    		} else {
			    		i.putExtra("cid", cid);
			    		i.putExtra("bid", -1L);
			    		i.putExtra("name", selected_name);
			    		i.putExtra("last_seen_eid", 0);
			    		i.putExtra("min_eid", 0);
			    		i.putExtra("type", "conversation");
			    		i.putExtra("joined", 1);
			    		i.putExtra("archived", 0);
		    		}
		    		startActivity(i);
		    		break;
		    	}
		    	dialog.dismiss();
		    }
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
