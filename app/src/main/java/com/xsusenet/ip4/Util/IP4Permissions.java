package com.xsusenet.ip4.Util;

import android.Manifest;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.xsusenet.ip4.R;

import java.util.ArrayList;
import java.util.List;

import static com.xsusenet.ip4.Util.PermissionsUtils.requestPermissions;


public class IP4Permissions {
    private final LinearLayout snackBarLayout;
    private final Activity activity;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    public IP4Permissions(Activity activity, LinearLayout linearLayout) {
        this.activity = activity;
        this.snackBarLayout = linearLayout;
    }

    public void checkRuntimePermissionForStorage() {
        if (PermissionsUtils.checkSelfForStoragePermission(activity)) {
            requestStoragePermissions();
        }
    }

    public void checkRuntimepermissionforLocation() {
        if (PermissionsUtils.checkSelfPermissionForLocation(activity)) {
            requestLocationPermissions();
        }
    }


    public void requestStoragePermissions() {
        if (PermissionsUtils.shouldShowRequestForStoragePermission(activity)) {
            showSnackBar(R.string.storage_permission, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE);
        } else {
            requestPermissions(activity, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE);
        }
    }

    public void requestLocationPermissions() {
        if (PermissionsUtils.shouldShowRequestForLocationPermission(activity)) {
            showSnackBar(R.string.location_permission, PermissionsUtils.PERMISSIONS_LOCATION, PermissionsUtils.REQUEST_LOCATION);
        } else {
            requestPermissions(activity, PermissionsUtils.PERMISSIONS_LOCATION, PermissionsUtils.REQUEST_LOCATION);
        }
    }

    public void requestAudio() {
        if (PermissionsUtils.shouldShowRequestForLocationPermission(activity)) {
            showSnackBar(R.string.record_audio, PermissionsUtils.PERMISSIONS_LOCATION, PermissionsUtils.REQUEST_AUDIO_RECORD);
        } else {
            requestPermissions(activity, PermissionsUtils.PERMISSIONS_RECORD_AUDIO, PermissionsUtils.REQUEST_AUDIO_RECORD);
        }
    }

    public void requestCallPermission() {
        if (PermissionsUtils.shouldShowRequestForCallPermission(activity)) {
            showSnackBar(R.string.phone_call_permission, PermissionsUtils.PERMISSION_CALL, PermissionsUtils.REQUEST_CALL_PHONE);
        } else {
            requestPermissions(activity, PermissionsUtils.PERMISSION_CALL, PermissionsUtils.REQUEST_CALL_PHONE);
        }
    }

    public void requestCameraPermission() {
        if (PermissionsUtils.shouldShowRequestForCameraPermission(activity)) {
            showSnackBar(R.string.phone_camera_permission, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA);
        } else {
            requestPermissions(activity, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA);
        }
    }

    public void requestContactPermission() {
        if (PermissionsUtils.shouldShowRequestForContactPermission(activity)) {
            showSnackBar(R.string.contact_permission, PermissionsUtils.PERMISSION_CONTACT, PermissionsUtils.REQUEST_CONTACT);
        } else {
            requestPermissions(activity, PermissionsUtils.PERMISSION_CONTACT, PermissionsUtils.REQUEST_CONTACT);
        }
    }

    public void requestCameraPermissionForProfilePhoto() {
        if (PermissionsUtils.shouldShowRequestForCameraPermission(activity)) {
            showSnackBar(R.string.phone_camera_permission, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA_FOR_PROFILE_PHOTO);
        } else {
            requestPermissions(activity, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA_FOR_PROFILE_PHOTO);
        }
    }

    public void requestStoragePermissionsForProfilePhoto() {
        if (PermissionsUtils.shouldShowRequestForStoragePermission(activity)) {
            showSnackBar(R.string.storage_permission, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE_FOR_PROFILE_PHOTO);
        } else {
            requestPermissions(activity, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE_FOR_PROFILE_PHOTO);
        }
    }

    public void requestCameraAndRecordPermission() {
        if (PermissionsUtils.shouldShowRequestForContactPermission(activity)) {
            showSnackBar(!PermissionsUtils.checkPermissionForCameraAndMicrophone(activity) ? R.string.camera_audio_permission : !PermissionsUtils.isAudioRecordingPermissionGranted(activity) ? R.string.record_audio : !PermissionsUtils.isCameraPermissionGranted(activity) ? R.string.phone_camera_permission : R.string.camera_audio_permission, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, PermissionsUtils.REQUEST_CAMERA_AUDIO);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, PermissionsUtils.REQUEST_CAMERA_AUDIO);
        }
    }

    public void ChecKruntimepermissionCameraAndStoragePermission() {

        if (PermissionsUtils.shouldShowRequestStorageandCamera(activity)) {
            if(activity != null)
            showSnackBar(!PermissionsUtils.checkpermissionforstorageandcamera(activity) ? R.string.camera_storage_permission : !PermissionsUtils.isCameraPermissionGranted(activity) ? R.string.phone_camera_permission : !PermissionsUtils.isStoragePermissionGranted(activity) ? R.string.storage_permission : R.string.camera_storage_permission, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionsUtils.REQUEST_STORAGE_ACAMERA);
        } else {
            final List<String> permissionsList = new ArrayList<String>();
            permissionsList.add(Manifest.permission.CAMERA);
            permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissionsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(activity != null)
            requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        }


    }

    public void ChecKruntimepermissionLocationAndStoragePermission() {

        if (PermissionsUtils.shouldShowRequestStorageandCamera(activity)) {
            showSnackBar(!PermissionsUtils.checkpermissionforstorageandcamera(activity) ? R.string.location_storage_permission : !PermissionsUtils.isLocationPermissionIsGranted(activity) ? R.string.location_permission : !PermissionsUtils.isStoragePermissionGranted(activity) ? R.string.storage_permission : R.string.location_storage_permission, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionsUtils.REQUEST_LOCATION_STORAGE);
        } else {
            final List<String> permissionsList = new ArrayList<String>();
            permissionsList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissionsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        }


    }

    public void checkRuntimePermissionForCameraAndAudioRecording() {
        if (PermissionsUtils.checkSelfPermissionForLocation(activity)) {
            requestCameraAndRecordPermission();
        }
    }


    public void showSnackBar(int resId, final String[] permissions, final int requestCode) {
        if (resId != -1)
            Snackbar.make(snackBarLayout, resId,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            requestPermissions(activity, permissions, requestCode);
                        }
                    }).show();
    }

}
