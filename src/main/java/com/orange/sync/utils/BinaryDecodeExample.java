//package com.orange.sync.utils;
//
//import com.orange.sync.enums.ApiMessageEnum;
//import com.orange.sync.enums.InputTypeEnum;
//
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BinaryDecodeExample {
//    public static void main(String[] args) {
//        byte[] buffer = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int index = 0;
//        ByteBuffer view = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);
//
//        int name = view.get(index++) & 0xFF;
//
//        if (name == ApiMessageEnum.MSG_ClientSync) {
//            int frameId = view.getInt(index);
//            index += 4;
//            int inputType = view.get(index++);
//            if (inputType == InputTypeEnum.ActorMove) {
//                Object input = decodeActorMove(view, index);
//                System.out.println("Name: " + name);
//                System.out.println("Data: {");
//                System.out.println("  FrameId: " + frameId);
//                System.out.println("  Input: " + input);
//                System.out.println("}");
//            } else if (inputType == InputTypeEnum.WeaponShoot) {
//                Object input = decodeWeaponShoot(view, index);
//                System.out.println("Name: " + name);
//                System.out.println("Data: {");
//                System.out.println("  FrameId: " + frameId);
//                System.out.println("  Input: " + input);
//                System.out.println("}");
//            } else {
//                Object input = decodeTimePast(view, index);
//                System.out.println("Name: " + name);
//                System.out.println("Data: {");
//                System.out.println("  FrameId: " + frameId);
//                System.out.println("  Input: " + input);
//                System.out.println("}");
//            }
//        } else if (name == ApiMessageEnum.MSG_ServerSync) {
//            int lastFrameId = view.getInt(index);
//            index += 4;
//            int len = view.get(index++);
//            List<Object> inputs = new ArrayList<>();
//            for (int i = 0; i < len; i++) {
//                int inputType = view.get(index++);
//                if (inputType == InputTypeEnum.ActorMove) {
//                    inputs.add(decodeActorMove(view, index));
//                    index += 13;
//                } else if (inputType == InputTypeEnum.WeaponShoot) {
//                    inputs.add(decodeWeaponShoot(view, index));
//                    index += 17;
//                } else {
//                    inputs.add(decodeTimePast(view, index));
//                    index += 4;
//                }
//            }
//            System.out.println("Name: " + ApiMessageEnum.MSG_ServerSync);
//            System.out.println("Data: {");
//            System.out.println("  LastFrameId: " + lastFrameId);
//            System.out.println("  Inputs: " + inputs);
//            System.out.println("}");
//        } else {
//            byte[] data = new byte[buffer.length - 1];
//            view.get(data);
//            String jsonData = new String(data);
//            System.out.println("Name: " + name);
//            System.out.println("Data: " + jsonData);
//        }
//    }
//
//    private static Object decodeActorMove(ByteBuffer view, int index) {
//        // Decode ActorMove input
//    }
//
//    private static Object decodeWeaponShoot(ByteBuffer view, int index) {
//        // Decode WeaponShoot input
//    }
//
//    private static Object decodeTimePast(ByteBuffer view, int index) {
//        // Decode TimePast input
//    }
//}