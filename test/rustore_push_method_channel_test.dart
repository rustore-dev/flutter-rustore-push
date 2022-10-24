import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:rustore_push/rustore_push_method_channel.dart';

void main() {
  MethodChannelRustorePush platform = MethodChannelRustorePush();
  const MethodChannel channel = MethodChannel('rustore_push');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
