/* RTcmix  - Copyright (C) 2004  The RTcmix Development Team
   See ``AUTHORS'' for a list of contributors. See ``LICENSE'' for
   the license to this software and for a DISCLAIMER OF ALL WARRANTIES.
*/

/* Functions for dynamically loading shared objects
 * DS, 10/2004
*/

#include "load_utils.h"

#include <dlfcn.h>

void *
find_dso(const char *loadPath)
{
	dlerror();	// clear error queue
	return dlopen(loadPath, RTLD_LAZY);
}

void *
find_symbol(void *dso, const char *symbol)
{
	dlerror();	// clear error queue
	return dlsym(dso, symbol);
}

int
unload_dso(void *dso)
{
	return dlclose(dso);
}

const char *get_dso_error()
{
	return dlerror();
}
