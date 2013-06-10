include $(CLEAR_VARS)

source	:= $(wildcard $(LOCAL_PATH)/genlib/*.c)
source	+= $(wildcard $(LOCAL_PATH)/genlib/*.cpp)

LOCAL_MODULE    := gen
LOCAL_CFLAGS	:= -Werror
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
LOCAL_SRC_FILES := $(source:$(LOCAL_PATH)%=%)
include $(BUILD_STATIC_LIBRARY)
