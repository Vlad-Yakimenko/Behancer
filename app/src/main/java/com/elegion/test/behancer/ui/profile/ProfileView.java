package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.common.BaseView;
import com.example.domain.model.user.User;

public interface ProfileView extends BaseView {

    void showProfile(User user);
}
