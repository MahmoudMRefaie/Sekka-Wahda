// Generated code from Butter Knife. Do not modify!
package com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mahmoudrefaie.sekkawahda.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public final class MyProfile_ViewBinding implements Unbinder {
  private MyProfile target;

  @UiThread
  public MyProfile_ViewBinding(MyProfile target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyProfile_ViewBinding(MyProfile target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.showProfilePic = Utils.findRequiredViewAsType(source, R.id.show_image, "field 'showProfilePic'", CircleImageView.class);
    target.username = Utils.findRequiredViewAsType(source, R.id.username, "field 'username'", TextView.class);
    target.ratingBar = Utils.findRequiredViewAsType(source, R.id.ratingBar, "field 'ratingBar'", RatingBar.class);
    target.editUsername = Utils.findRequiredViewAsType(source, R.id.edit_username, "field 'editUsername'", ImageView.class);
    target.city = Utils.findRequiredViewAsType(source, R.id.city_result, "field 'city'", TextView.class);
    target.editCity = Utils.findRequiredViewAsType(source, R.id.edit_city, "field 'editCity'", ImageView.class);
    target.email = Utils.findRequiredViewAsType(source, R.id.email_result, "field 'email'", TextView.class);
    target.editEmail = Utils.findRequiredViewAsType(source, R.id.edit_email, "field 'editEmail'", ImageView.class);
    target.phoneNumber = Utils.findRequiredViewAsType(source, R.id.phone_result, "field 'phoneNumber'", TextView.class);
    target.editPhone = Utils.findRequiredViewAsType(source, R.id.edit_phone, "field 'editPhone'", ImageView.class);
    target.ssn = Utils.findRequiredViewAsType(source, R.id.ssn_result, "field 'ssn'", TextView.class);
    target.carImage = Utils.findRequiredViewAsType(source, R.id.car_image_img, "field 'carImage'", ImageView.class);
    target.editCarImage = Utils.findRequiredViewAsType(source, R.id.edit_car_image, "field 'editCarImage'", ImageView.class);
    target.driverLicenseLayout = Utils.findRequiredViewAsType(source, R.id.driver_license, "field 'driverLicenseLayout'", RelativeLayout.class);
    target.driverLicense = Utils.findRequiredViewAsType(source, R.id.driver_license_result, "field 'driverLicense'", TextView.class);
    target.editDriverLicense = Utils.findRequiredViewAsType(source, R.id.edit_driver_license, "field 'editDriverLicense'", ImageView.class);
    target.carLicense = Utils.findRequiredViewAsType(source, R.id.car_license_result, "field 'carLicense'", TextView.class);
    target.editCarLicense = Utils.findRequiredViewAsType(source, R.id.edit_car_license, "field 'editCarLicense'", ImageView.class);
    target.carModel = Utils.findRequiredViewAsType(source, R.id.car_model_result, "field 'carModel'", TextView.class);
    target.editCarModel = Utils.findRequiredViewAsType(source, R.id.edit_car_model, "field 'editCarModel'", ImageView.class);
    target.profileProgressBar = Utils.findRequiredViewAsType(source, R.id.profile_progress_bar, "field 'profileProgressBar'", ProgressBar.class);
  }

  @Override
  public void unbind() {
    MyProfile target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.showProfilePic = null;
    target.username = null;
    target.ratingBar = null;
    target.editUsername = null;
    target.city = null;
    target.editCity = null;
    target.email = null;
    target.editEmail = null;
    target.phoneNumber = null;
    target.editPhone = null;
    target.ssn = null;
    target.carImage = null;
    target.editCarImage = null;
    target.driverLicenseLayout = null;
    target.driverLicense = null;
    target.editDriverLicense = null;
    target.carLicense = null;
    target.editCarLicense = null;
    target.carModel = null;
    target.editCarModel = null;
    target.profileProgressBar = null;
  }
}
