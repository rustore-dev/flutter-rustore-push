import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:rustorepush/rustorepush.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final push = RustorePushClient();

  @override
  void initState() {
    super.initState();
    initPush();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPush() async {
    push.initialize();

    if (!mounted) return;

    setState(() {
      // _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: const Center(
          child: Text('Test push'),
        ),
      ),
    );
  }
}
