import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:rustorepush/rustorepush_method_channel.dart';

void main() {
  MethodChannelRustorePush platform = MethodChannelRustorePush();
  const MethodChannel channel = MethodChannel('rustorepush');

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
