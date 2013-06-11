include $(CLEAR_VARS)

source	:= $(wildcard $(LOCAL_PATH)/src/rtcmix/gen/*.c)
source	+= $(wildcard $(LOCAL_PATH)/src/rtcmix/heap/*.cpp)
source	+= $(wildcard $(LOCAL_PATH)/src/rtcmix/*.c)
source	+= $(wildcard $(LOCAL_PATH)/src/rtcmix/*.cpp)

LOCAL_MODULE    := rtcmix
LOCAL_CFLAGS	:= -Werror
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix/heap
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix/gen
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/conf
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/include
LOCAL_STATIC_LIBRARIES := gen
LOCAL_SRC_FILES := $(source:$(LOCAL_PATH)%=%) \
	insts/joel/jfuncs/jfuncs.c \
	insts/joel/tuning/tuning.c \

include $(BUILD_SHARED_LIBRARY)
