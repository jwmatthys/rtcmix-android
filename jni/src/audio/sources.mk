include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= src/audio/AudioDevice.cpp src/audio/AudioIODevice.cpp src/audio/AudioDeviceImpl.cpp src/audio/ThreadedAudioDevice.cpp src/audio/AudioOutputGroupDevice.cpp src/audio/DualOutputAudioDevice.cpp src/audio/AudioFileDevice.cpp src/audio/NetAudioDevice.cpp src/audio/audio_dev_creator.cpp src/audio/sndlibsupport.c src/audio/OSSAudioDevice.cpp src/audio/SinglePortOSSAudioDevice.cpp src/audio/MultiPortOSSAudioDevice.cpp src/audio/ALSAAudioDevice.cpp src/audio/TestAudioDevice.cpp
LOCAL_MODULE    := rtaudio
LOCAL_CFLAGS	:= -Werror # -fexceptions
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix

include $(BUILD_STATIC_LIBRARY)
