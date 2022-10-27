import 'package:pigeon/pigeon.dart';

@HostApi()
abstract class PushClient {
  @async
  String initialize(String project);
}
