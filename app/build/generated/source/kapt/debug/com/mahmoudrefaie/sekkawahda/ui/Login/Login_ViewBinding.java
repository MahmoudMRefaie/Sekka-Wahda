// Generated code from Butter Knife. Do not modify!
package com.mahmoudrefaie.sekkawahda.ui.Login;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputLayout;
import com.mahmoudrefaie.sekkawahda.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Login_ViewBinding implements Unbinder {
  private Login target;

  @UiThread
  public Login_ViewBinding(Login target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Login_ViewBinding(Login target, View source) {
    this.target = target;

    target.etUname = Utils.findRequiredViewAsType(source, R.id.username, "field 'etUname'", TextInputLayout.class);
    target.etPass = Utils.findRequiredViewAsType(source, R.id.password, "field 'etPass'", TextInputLayout.class);
    target.btnLogin = Utils.findRequiredViewAsType(source, R.id.btnLogin, "field 'btnLogin'", Button.class);
    target.forget = Utils.findRequiredViewAsType(source, R.id.forget, "field 'forget'", TextView.class);
    target.register = Utils.findRequiredViewAsType(source, R.id.register, "field 'register'", TextView.class);
    target.terms = Utils.findRequiredViewAsType(source, R.id.terms, "field 'terms'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Login target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etUname = null;
    target.etPass = null;
    target.btnLogin = null;
    target.forget = null;
    target.register = null;
    target.terms = null;
  }
}
