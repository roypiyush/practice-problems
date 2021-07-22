# Python stubs generated by omniidl from Hello.idl

import omniORB, _omnipy
from omniORB import CORBA, PortableServer
_0_CORBA = CORBA

_omnipy.checkVersion(3,0, __file__)


#
# Start of module "HelloApp"
#
__name__ = "HelloApp"
_0_HelloApp = omniORB.openModule("HelloApp", r"Hello.idl")
_0_HelloApp__POA = omniORB.openModule("HelloApp__POA", r"Hello.idl")


# interface Hello
_0_HelloApp._d_Hello = (omniORB.tcInternal.tv_objref, "IDL:HelloApp/Hello:1.0", "Hello")
omniORB.typeMapping["IDL:HelloApp/Hello:1.0"] = _0_HelloApp._d_Hello
_0_HelloApp.Hello = omniORB.newEmptyClass()
class Hello :
    _NP_RepositoryId = _0_HelloApp._d_Hello[1]

    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")

    _nil = CORBA.Object._nil


_0_HelloApp.Hello = Hello
_0_HelloApp._tc_Hello = omniORB.tcInternal.createTypeCode(_0_HelloApp._d_Hello)
omniORB.registerType(Hello._NP_RepositoryId, _0_HelloApp._d_Hello, _0_HelloApp._tc_Hello)

# Hello operations and attributes
Hello._d_sayHello = ((), ((omniORB.tcInternal.tv_string,0), ), None)
Hello._d_shutdown = ((), None, None)

# Hello object reference
class _objref_Hello (CORBA.Object):
    _NP_RepositoryId = Hello._NP_RepositoryId

    def __init__(self):
        CORBA.Object.__init__(self)

    def sayHello(self, *args):
        return _omnipy.invoke(self, "sayHello", _0_HelloApp.Hello._d_sayHello, args)

    def shutdown(self, *args):
        return _omnipy.invoke(self, "shutdown", _0_HelloApp.Hello._d_shutdown, args)

    __methods__ = ["sayHello", "shutdown"] + CORBA.Object.__methods__

omniORB.registerObjref(Hello._NP_RepositoryId, _objref_Hello)
_0_HelloApp._objref_Hello = _objref_Hello
del Hello, _objref_Hello

# Hello skeleton
__name__ = "HelloApp__POA"
class Hello (PortableServer.Servant):
    _NP_RepositoryId = _0_HelloApp.Hello._NP_RepositoryId


    _omni_op_d = {"sayHello": _0_HelloApp.Hello._d_sayHello, "shutdown": _0_HelloApp.Hello._d_shutdown}

Hello._omni_skeleton = Hello
_0_HelloApp__POA.Hello = Hello
omniORB.registerSkeleton(Hello._NP_RepositoryId, Hello)
del Hello
__name__ = "HelloApp"

#
# End of module "HelloApp"
#
__name__ = "Hello_idl"

_exported_modules = ( "HelloApp", )

# The end.
