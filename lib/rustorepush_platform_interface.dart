import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'rustorepush_method_channel.dart';

abstract class RustorePushPlatform extends PlatformInterface {
  /// Constructs a RustorePushPlatform.
  RustorePushPlatform() : super(token: _token);

  static final Object _token = Object();

  static RustorePushPlatform _instance = MethodChannelRustorePush();

  /// The default instance of [RustorePushPlatform] to use.
  ///
  /// Defaults to [MethodChannelRustorePush].
  static RustorePushPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [RustorePushPlatform] when
  /// they register themselves.
  static set instance(RustorePushPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
