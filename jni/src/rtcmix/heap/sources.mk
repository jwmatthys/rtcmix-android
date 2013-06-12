include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= src/rtcmix/heap/heap.cpp src/rtcmix/heap/queue.cpp src/rtcmix/heap/rtQueue.cpp
LOCAL_MODULE    := rtheap
#LOCAL_CFLAGS	:= -Werror # -fexceptions
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/include

include $(BUILD_STATIC_LIBRARY)
