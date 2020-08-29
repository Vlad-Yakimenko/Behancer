package com.elegion.test.behancer.ui.projects;

import androidx.fragment.app.Fragment;

import com.elegion.test.behancer.common.SingleFragmentActivity;

public class ProjectsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return FeedProjectsFragment.newInstance();
    }
}
