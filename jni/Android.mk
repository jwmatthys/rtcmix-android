# Copyright (C) 2009 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# build libgen (libgen.a)
include $(LOCAL_PATH)/genlib/sources.mk
# build JG's objlib (now libobj.a)
include $(LOCAL_PATH)/insts/jg/objlib/sources.mk
# build stklib (now libstk.a)
include $(LOCAL_PATH)/insts/stk/stklib/sources.mk
# build librtaudio (libstk.a)
include $(LOCAL_PATH)/src/audio/sources.mk
# build sndlib (libsnd.a)
include $(LOCAL_PATH)/src/sndlib/sources.mk
# build libconf (libconf.a)
# we'll see if flex and bison results work ok
include $(LOCAL_PATH)/src/conf/sources.mk
# build libinlet (libinlet.a)
# this will go eventually but should serve as a template for access
include $(LOCAL_PATH)/src/control/maxmsp/sources.mk
# build libpfbus (libpfbus.a)
include $(LOCAL_PATH)/src/control/pfbus/sources.mk
# build libminc (libminc.a)
include $(LOCAL_PATH)/src/parser/minc/sources.mk
# build librtheap (librtheap.a)
include $(LOCAL_PATH)/src/rtcmix/heap/sources.mk

# build full librtcmix.so
include $(LOCAL_PATH)/rtcmix.mk
