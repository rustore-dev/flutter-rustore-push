import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:rustorepush/rustorepush.dart';

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

  @override
  void initState() {
    super.initState();

    initPush();
  }

  void initPush() {
    final push = RustorePushClient();
    push.initialize("BAiyQBm7DkIh4RhZvbq49rYjF0DXJlis").then((value) {
      print("initialize success: ${value}");
      setState(() {
        token = value;
      });
    }, onError: (value) {
      print("initialize error: ${value}");
      setState(() {
        error = value;
      });
    });

    push.onNewToken().then((value) {
      print("onNewToken: ${value}");

      setState(() {
        token = value;
      });
    });

    push.onError().then((value) {
      print("onError: ${value}");

      setState(() {
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
        body: Container(
          alignment: Alignment.center,
          color: Colors.green,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const Text('Test push'),
              const SizedBox(height: 24),
              Text('token: ${token}'),
              Text('error: ${error}')
            ],
          ),
        ),
      ),
    );
  }
}
