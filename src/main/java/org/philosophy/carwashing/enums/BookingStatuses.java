package org.philosophy.carwashing.enums;

/**
 * Статусы бронирования
 */
public enum BookingStatuses {
    /**
     * Созданная, но не подтвержденная оператором бронь
     */
    NEW,
    /**
     * Подтвержденная оператором бронь
     */
    ADMITTED,
    /**
     * Отмененная клиентом бронь
     */
    CANCELLED,
    /**
     * Автоматически отмененная системой бронь
     */
    AUTO_CANCELLED,
    /**
     * Удаленная оператором бронь
     */
    DELETED
}
