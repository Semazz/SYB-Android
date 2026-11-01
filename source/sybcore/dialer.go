package core

import (
	"errors"
	"sync"
	"syscall"

	"github.com/xtls/xray-core/transport/internet"
)

var (
	controllerOnce sync.Once
	platformGuard  sync.RWMutex
	activePlatform Platform
)

func installDialerController(platform Platform) {
	platformGuard.Lock()
	activePlatform = platform
	platformGuard.Unlock()

	controllerOnce.Do(func() {
		_ = internet.RegisterDialerController(protectSocket)
		_ = internet.RegisterListenerController(protectSocket)
	})
}

func clearPlatform(platform Platform) {
	platformGuard.Lock()
	if activePlatform == platform {
		activePlatform = nil
	}
	platformGuard.Unlock()
}

func protectSocket(network string, address string, conn syscall.RawConn) error {
	platformGuard.RLock()
	platform := activePlatform
	platformGuard.RUnlock()

	if platform == nil {
		return nil
	}
	if conn == nil {
		return errors.New("SYBcore: socket control is unavailable")
	}
	protected := false
	if err := conn.Control(func(fd uintptr) {
		protected = platform.Protect(int32(fd))
	}); err != nil {
		return err
	}
	if !protected {
		return errors.New("SYBcore: Android refused to protect the socket")
	}
	return nil
}
