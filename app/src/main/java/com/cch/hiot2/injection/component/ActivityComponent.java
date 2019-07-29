package com.cch.hiot2.injection.component;


import com.cch.hiot2.injection.PerActivity;
import com.cch.hiot2.injection.module.ActivityModule;
import com.cch.hiot2.ui.datahistory.DataHistoryActivity;
import com.cch.hiot2.ui.devicedetail.DeviceDetailActivity;
import com.cch.hiot2.ui.gpsdata.GpsHistoryActivity;
import com.cch.hiot2.ui.login.LoginActivity;
import com.cch.hiot2.ui.main.MainActivity;
import com.cch.hiot2.ui.main.equipment.EquipmentFragment;
import com.cch.hiot2.ui.main.message.MessageFragment;
import com.cch.hiot2.ui.main.mine.MineFragment;
import com.cch.hiot2.ui.main.scene.SceneFragment;
import com.cch.hiot2.ui.register.RegisterActivity;
import com.cch.hiot2.ui.zxing.ZxingActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(MessageFragment messageFragment);

    void inject(EquipmentFragment equipmentFragment);

    void inject(MineFragment mineFragment);

    void inject(SceneFragment sceneFragment);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(ZxingActivity zxingActivity);

    void inject(DeviceDetailActivity deviceDetailActivity);

    void inject(DataHistoryActivity dataHistoryActivity);

    void inject(GpsHistoryActivity gpsHistoryActivity);
}























