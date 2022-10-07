package Lambdas;

import java.time.ZoneId;

/**Lambda used to notifications.
 * Takes in a zone ID and changes it to a notification message
 */
public interface TimeZone {
    String getTime(ZoneId now);
}
