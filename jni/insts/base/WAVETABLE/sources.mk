include $(CLEAR_VARS)
LOCAL_MODULE    := WAVETABLE
LOCAL_CFLAGS	:= -Werror
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
LOCAL_STATIC_LIBRARIES := gen
LOCAL_SRC_FILES := insts/base/WAVETABLE/WAVETABLE.cpp
include $(BUILD_STATIC_LIBRARY)