import 'package:flutter_test/flutter_test.dart';
import 'package:rustore_push/rustore_push.dart';
import 'package:rustore_push/rustore_push_platform_interface.dart';
import 'package:rustore_push/rustore_push_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockRustorePushPlatform
    with MockPlatformInterfaceMixin
    implements RustorePushPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final RustorePushPlatform initialPlatform = RustorePushPlatform.instance;

  test('$MethodChannelRustorePush is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelRustorePush>());
  });

  test('getPlatformVersion', () async {
    RustorePush rustorePushPlugin = RustorePush();
    MockRustorePushPlatform fakePlatform = MockRustorePushPlatform();
    RustorePushPlatform.instance = fakePlatform;

    expect(await rustorePushPlugin.getPlatformVersion(), '42');
  });
}
