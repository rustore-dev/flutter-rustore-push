import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'rustorepush_platform_interface.dart';

/// An implementation of [RustorePushPlatform] that uses method channels.
class MethodChannelRustorePush extends RustorePushPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('rustore_push');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
