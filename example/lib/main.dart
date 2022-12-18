import 'package:flutter/material.dart';
import 'package:flutter_rustore_push/flutter_rustore_push.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String token = "";
  String error = "";
  String message = "";
  List<String> stack = [];

  @override
  void initState() {
    super.initState();

    initPush();
  }

  void initPush() {
    RustorePushClient.initialize("jYqD02VNCyrXKvlyLv3sCwCPkjlFCvqy").then((value) {
      final item = "initialize success: ${value}";

      print(item);

      setState(() {
        stack.add(item);
        token = value;
      });
    }, onError: (err) {
      final item = "initialize error: ${err}";

      print(item);

      setState(() {
        stack.add(item);
        error = err.toString();
      });
    });

    RustorePushClient.available().then((value) {
      final item = "available success: ${value}";

      print(item);

      setState(() {
        stack.add(item);
      });
    }, onError: (err) {
      final item = "available error: ${err}";

      print(item);

      setState(() {
        stack.add(item);
        error = err.toString();
      });
    });

    RustorePushClient.onNewToken().then((value) {
      final item = "on new token success: ${value}";

      print(item);

      setState(() {
        stack.add(item);
        token = value;
      });
    }, onError: (err) {
      final item = "on new token err: ${err}";

      print(item);

      setState(() {
        stack.add(item);
        error = err.toString();
      });
    });

    RustorePushClient.onMessageReceived().then((value) {
      final item = "on message received success: ${value}";

      print(item);

      setState(() {
        stack.add(item);
        message = value.toString();
      });
    }, onError: (err) {
      final item = "on message received error: ${err}";

      print(item);

      setState(() {
        stack.add(item);
        error = err.toString();
      });
    });

    RustorePushClient.onError().then((value) {
      final item = "on error: ${value}";

      print(item);

      setState(() {
        stack.add(item);
        error = value;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('RuStore PushSDK Example'),
        ),
        body: SingleChildScrollView(
          child: Padding(
            padding: EdgeInsets.symmetric(horizontal: 24),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                ElevatedButton(
                  onPressed: () {
                    RustorePushClient.getToken().then((value) {
                      final item = "get token success: ${value}";

                      print(item);

                      setState(() {
                        stack.add(item);
                        token = value.toString();
                      });
                    }, onError: (err) {
                      final item = "get token error: ${err}";

                      print(item);

                      setState(() {
                        stack.add(item);
                        error = err.toString();
                      });
                    });
                  },
                  child: const Text("Get token"),
                ),
                ElevatedButton(
                  onPressed: () {
                    RustorePushClient.deleteToken().then((value) {
                      final item = "delete token success";

                      print(item);

                      setState(() {
                        stack.add(item);
                      });
                    }, onError: (err) {
                      final item = "delete token error: ${err}";

                      print(item);

                      setState(() {
                        stack.add(item);
                        error = err.toString();
                      });
                    });
                  },
                  child: const Text("Delete token"),
                ),
                SizedBox(height: 8),
                for (final item in stack) ...[
                  Text(item),
                  SizedBox(height: 4),
                ],
              ],
            ),
          ),
        ),
      ),
    );
  }
}
