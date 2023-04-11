public class SwerveModule {
    public double xOffset;
    public double yOffset;
    public double currentAngle;

    public SwerveModule(double xOffset, double yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        currentAngle = 0;
    }

    public void calculate(double targetForwardVelocity, double targetSidewaysVelocity, double targetRotationalVelocity) {
        double xVector = targetForwardVelocity - targetRotationalVelocity * yOffset;
        double yVector = targetSidewaysVelocity + targetRotationalVelocity * xOffset;
        double rawVelocity = Math.sqrt(Math.pow(xVector, 2) + Math.pow(yVector, 2));
        double rawAngle = Math.atan2(yVector, xVector);
        System.out.println("Current Angle " + currentAngle);
        System.out.println("Raw Angle: " + rawAngle);
        System.out.println("Raw Velocity: " + rawVelocity);
        optimize(rawAngle, rawVelocity);
    }

    public void optimize(double initialtargetAngle, double targetVelocity1){
        double targetAngle = initialtargetAngle - currentAngle;
        double oppositeAngle = targetAngle + Math.PI;
        double reverseTargetAngle = -Math.signum(targetAngle) * (Math.abs(2*Math.PI - targetAngle));
        double reverseOppositeAngle = -Math.signum(oppositeAngle) * Math.abs(2*Math.PI - oppositeAngle);
        boolean reverseSpeed = Math.min(Math.abs(oppositeAngle), Math.abs(reverseOppositeAngle)) < Math.min(Math.abs(targetAngle), Math.abs(reverseTargetAngle));
        double bestAngle = MathContainer.closestToZero(targetAngle, oppositeAngle, reverseTargetAngle, reverseOppositeAngle);
        double bestVelocity = reverseSpeed ? -targetVelocity1 : targetVelocity1;

        System.out.println("Best Angle: " + bestAngle);
        System.out.println("Best Velocity: " + bestVelocity);
        currentAngle += bestAngle;
    }
}
