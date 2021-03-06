/**
 * Copyright 2012 OpenKit
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openkit.user;

import io.openkit.OKUser;
import io.openkit.R;

import com.facebook.widget.ProfilePictureView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class OKUserProfileFragment extends Fragment 
{
	private TextView userNickTextView;
	private ProfilePictureView profiePictureView;
	private Button logoutButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		setRetainInstance(true);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.io_openkit_fragment_userprofile, container, false);
		
		OKUser currentUser = OKUser.getCurrentUser();
		
		userNickTextView = (TextView)view.findViewById(R.id.io_openkit_userNickTextView);
		profiePictureView = (ProfilePictureView)view.findViewById(R.id.io_openkit_fbProfilePicView);
		logoutButton = (Button)view.findViewById(R.id.io_openkit_logoutButton);
		logoutButton.setOnClickListener(logoutButtonClicked);
		
		userNickTextView.setText(currentUser.getUserNick());
		profiePictureView.setProfileId(Long.toString(currentUser.getFBUserID()));
		
		return view;
	}
	
	private OnClickListener logoutButtonClicked = new OnClickListener() 
	{
		@Override
		public void onClick(View v) {
			OKUser.logoutCurrentUser(OKUserProfileFragment.this.getActivity());
			OKUserProfileFragment.this.getActivity().finish();
		}
	};

}
