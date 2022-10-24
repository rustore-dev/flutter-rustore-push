
import 'rustorepush_platform_interface.dart';

class RustorePush {
  Future<String?> getPlatformVersion() {
    return RustorePushPlatform.instance.getPlatformVersion();
  }
}
