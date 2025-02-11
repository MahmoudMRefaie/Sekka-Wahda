// Generated code from Butter Knife. Do not modify!
package com.mahmoudrefaie.sekkawahda.ui.Registeration;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mahmoudrefaie.sekkawahda.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public final class Registeration_ViewBinding implements Unbinder {
  private Registeration target;

  @UiThread
  public Registeration_ViewBinding(Registeration target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Registeration_ViewBinding(Registeration target, View source) {
    this.target = target;

    target.login = Utils.findOptionalViewAsType(source, R.id.login, "field 'login'", TextView.class);
    target.toolbar = Utils.findOptionalViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.signUp = Utils.findOptionalViewAsType(source, R.id.sign_up, "field 'signUp'", TextView.class);
  }

  @Override
  public void unbind() {
    Registeration target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.login = null;
    target.toolbar = null;
    target.signUp = null;
  }
}
