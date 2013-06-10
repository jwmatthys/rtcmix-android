include $(CLEAR_VARS)
LOCAL_MODULE    := tuning
LOCAL_CFLAGS	:= -Werror
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
LOCAL_STATIC_LIBRARIES := gen
LOCAL_SRC_FILES := insts/joel/tuning/tuning.c
include $(BUILD_SHARED_LIBRARY)
