# Admin Mode Integration Guide

This dialer app has been modified to support admin mode functionality with Firebase integration.

## What's Been Added

1. **Firebase Dependencies**: Added Firebase Firestore, Analytics, and Config dependencies
2. **Admin Mode Service**: Created `AdminModeService.java` to check admin mode status from Firebase
3. **Splash Screen Integration**: Modified `ActivitySplash.java` to check admin mode before proceeding
4. **Admin Module Placeholder**: Created `AdminModuleActivity.java` as a placeholder for your admin module

## Firebase Setup Required

### 1. Create Firebase Project
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project or use existing one
3. Add an Android app with package name: `com.callos16.callscreen.colorphone`

### 2. Download Configuration File
1. Download `google-services.json` from Firebase Console
2. Replace the placeholder `app/google-services.json` with your actual file

### 3. Set Up Firestore Database
1. In Firebase Console, go to Firestore Database
2. Create a collection named `admin_settings`
3. Add a document named `mode` with field `enabled` (boolean)
4. Set `enabled` to `true` to enable admin mode, `false` to disable

## How It Works

1. **App Launch**: When the app starts, it shows the splash screen
2. **Permission Check**: First checks for required permissions
3. **Admin Mode Check**: If permissions are granted, checks Firebase for admin mode status
4. **Routing**: 
   - If admin mode is enabled → navigates to `AdminModuleActivity`
   - If admin mode is disabled → navigates to normal dialer (`ActivityHome`)

## Integrating Your Admin Module

### Option 1: Replace Placeholder Activity
Replace the content of `AdminModuleActivity.java` with your admin module code.

### Option 2: Add as Separate Module
1. Create a new module in your project
2. Add your admin module source code to the new module
3. Update the splash screen to navigate to your admin module's main activity

### Option 3: External App Integration
If your admin module is a separate app:
1. Add your admin app as a dependency
2. Update the splash screen to launch your admin app using `PackageManager`
3. Handle the app switching logic appropriately

## Firebase Data Structure

The app expects this structure in Firestore:

```
admin_settings/
  └── mode/
      └── enabled: boolean
```

## Testing

1. Set up Firebase as described above
2. Build and run the app
3. Toggle the `enabled` field in Firestore to test both modes
4. Admin mode enabled → should show admin module
5. Admin mode disabled → should show normal dialer

## Next Steps

1. **Provide your admin module source code** - I can help integrate it properly
2. **Set up Firebase project** - Follow the setup instructions above
3. **Test the integration** - Verify both admin and normal modes work
4. **Customize as needed** - Modify the admin check logic or UI as required

## Files Modified

- `app/build.gradle` - Added Firebase dependencies
- `build.gradle` - Added Google Services plugin
- `app/src/main/AndroidManifest.xml` - Added admin module activity
- `app/src/main/java/com/callos16/callscreen/colorphone/ActivitySplash.java` - Added admin mode check
- `app/src/main/java/com/callos16/callscreen/colorphone/service/AdminModeService.java` - New Firebase service
- `app/src/main/java/com/callos16/callscreen/colorphone/admin/AdminModuleActivity.java` - Placeholder admin activity
- `app/google-services.json` - Firebase configuration template

## Support

If you need help integrating your specific admin module or have questions about the Firebase setup, please provide your admin module source code and I'll help you integrate it properly.
