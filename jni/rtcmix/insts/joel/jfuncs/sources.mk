SOURCES		:= jfuncs.c
LOCAL_MODULE    := jfuncs
LOCAL_CFLAGS	:= -Werror
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/rtcmix/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/rtcmix/genlib
LOCAL_SRC_FILES += $(SOURCES:%=rtcmix/insts/joel/jfuncs/%)

