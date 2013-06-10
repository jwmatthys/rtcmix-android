SOURCES		:= tuning.c
LOCAL_MODULE    := tuning
LOCAL_CFLAGS	:= -Werror
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/rtcmix/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/rtcmix/genlib
LOCAL_SRC_FILES += $(SOURCES:%=rtcmix/insts/joel/tuning/%)

