package com.mahmoudrefaie.sekkawahda.ui.MainPage

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.Login.Login
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home.HomeFragment
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.MoreFragment
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications.NotificationAdapter
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications.NotificationsFragment
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class MainPage : AppCompatActivity() {
    private var searchIcon:CircleImageView? = null
    private var toolbar: Toolbar? = null
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private var homeFragment: HomeFragment? = null
    private var notificationsFragment: NotificationsFragment? = null
    private var moreFragment: MoreFragment? = null

    //private NotificationBadge notifyBadge;
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle("Sekka Wahda")
        setSupportActionBar(toolbar)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        homeFragment = HomeFragment()
        notificationsFragment = NotificationsFragment()
        moreFragment = MoreFragment()
        tabLayout?.setupWithViewPager(viewPager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, 0)
        viewPagerAdapter.addFragment(homeFragment!!, "Home")
        viewPagerAdapter.addFragment(notificationsFragment!!, "Notifications")
        viewPagerAdapter.addFragment(moreFragment!!, "More")
        viewPager?.setAdapter(viewPagerAdapter)

        searchIcon = findViewById(R.id.search)
        searchIcon?.setOnClickListener(View.OnClickListener {
            var intent = Intent(this,Search::class.java)
            startActivity(intent)
        })

        //To Add Icons
        tabLayout?.getTabAt(0)!!.setIcon(R.drawable.ic_home_24dp)
        tabLayout?.getTabAt(0)!!.icon!!.setColorFilter(resources.getColor(R.color.proj_sub_color), PorterDuff.Mode.SRC_IN)
        tabLayout?.getTabAt(1)!!.setIcon(R.drawable.ic_notifications_none_black_24dp)
        tabLayout?.getTabAt(2)!!.setIcon(R.drawable.ic_menu_black_24dp)

        //Notification Badge
        val notificationBadgeDrawable = tabLayout?.getTabAt(1)!!.orCreateBadge
        notificationBadgeDrawable.isVisible = true
        tabLayout?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> tabLayout?.getTabAt(0)!!.icon!!.setColorFilter(resources.getColor(R.color.proj_sub_color), PorterDuff.Mode.SRC_IN)
                    1 -> {
                        tabLayout?.getTabAt(1)!!.setIcon(R.drawable.ic_selected_notifications_24dp)
                        //notifyBadge.clear();
                        notificationBadgeDrawable.isVisible = false
                    }
                    2 -> tabLayout?.getTabAt(2)!!.icon!!.setColorFilter(resources.getColor(R.color.proj_sub_color), PorterDuff.Mode.SRC_IN)
                    else -> {
                        tabLayout?.getTabAt(0)!!.icon!!.setColorFilter(resources.getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN)
                        tabLayout?.getTabAt(1)!!.setIcon(R.drawable.ic_notifications_none_black_24dp)
                        tabLayout?.getTabAt(2)!!.icon!!.setColorFilter(resources.getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> tabLayout?.getTabAt(0)!!.icon!!.setColorFilter(resources.getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN)
                    1 -> tabLayout?.getTabAt(1)!!.setIcon(R.drawable.ic_notifications_none_black_24dp)
                    2 -> tabLayout?.getTabAt(2)!!.icon!!.setColorFilter(resources.getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN)
                    else -> {
                        tabLayout?.getTabAt(0)!!.icon!!.setColorFilter(resources.getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN)
                        tabLayout?.getTabAt(1)!!.setIcon(R.drawable.ic_notifications_none_black_24dp)
                        tabLayout?.getTabAt(2)!!.icon!!.setColorFilter(resources.getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private inner class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
        private val fragments: MutableList<Fragment> = ArrayList()
        private val fragmentTitles: MutableList<String> = ArrayList()
        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            fragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitles[position]
        }
    }

    private fun updateUI() {
        Toast.makeText(this, "You should login", Toast.LENGTH_LONG).show()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}